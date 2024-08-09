package org.pegasus.backendapi.family.service

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.family.FamilyAlreadyException
import org.pegasus.backendapi.family.FamilyNotExistException
import org.pegasus.backendapi.family.FamilyRepository
import org.pegasus.backendapi.family.model.FamilyMapper
import org.pegasus.backendapi.family.model.dto.FamilyDto
import org.pegasus.backendapi.family.model.dto.MemberTransactionDto
import org.pegasus.backendapi.family.model.entity.Family
import org.pegasus.backendapi.transaction.model.TransactionMapper
import org.pegasus.backendapi.user.UserMapper
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FamilyService(
    val transactionService: FamilyInternalService,
    val familyRepository: FamilyRepository,
    val userService: UserInternalService
) {

    private final val log = logger()

    fun create(): FamilyDto {
        val user = userService.currentUser()

        if (user.family != null) {
            throw FamilyAlreadyException(user.username)
        }

        val newFamily = Family()
        val family = familyRepository.save(newFamily)
        userService.updateFamily(user, family)
        return FamilyMapper.toDto(family)
    }

    @Transactional
    fun getMembers(): Set<MemberTransactionDto> {
        val currentUser = userService.currentUser()

        val familyId = currentUser.family?.id ?: throw FamilyNotExistException(currentUser.username)
        // TODO
        //  Как же это костыльно. Метод - Transactional, а полчить currentUser.family.members - нельзя...
        //  Я явно что-то не так делаю
        val family = familyRepository.findById(familyId)
            .orElseThrow { throw FamilyNotExistException(currentUser.username) }

        val members = family.members.filter { user -> user.family?.id == currentUser.family?.id }.toSet()
        val transactions = transactionService.getLatestForUsers(members)

        return members
            .map { user -> user to transactions.firstOrNull { transaction -> transaction.user == user }}
            .map { pair ->
                MemberTransactionDto(UserMapper.toDto(pair.first), pair.second?.let { TransactionMapper.toDto(it) })
            }.toSet()
    }

    fun getTransactions(): Set<MemberTransactionDto> {
        val currentUser = userService.currentUser()

        val familyId = currentUser.family?.id ?: throw FamilyNotExistException(currentUser.username)
        val family = familyRepository.findById(familyId).orElseThrow { throw FamilyNotExistException(currentUser.username) }

        val familyMembers = family.members.filter { user -> user.family?.id == currentUser.family?.id }.toSet()
        val transactionByUser = transactionService.getLatestForUsers(familyMembers)

        return transactionByUser
            .map { transaction -> transaction to familyMembers.first { user -> transaction.user == user } }
            .map { pair ->
                MemberTransactionDto(UserMapper.toDto(pair.second), TransactionMapper.toDto(pair.first))
            }.toSet()
    }

}
