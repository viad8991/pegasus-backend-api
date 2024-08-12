package org.pegasus.backendapi.utils

interface IMapper<R : IRequest, D : IDto, E : IEntity, S : IResponse> {

    fun toDto(request: R): D {
        throw UnsupportedOperationException("request->dto is not implemented")
    }

    fun toEntity(dto: D): E {
        throw UnsupportedOperationException("dto->entity is not implemented")
    }

    fun toDto(entity: E): D {
        throw UnsupportedOperationException("entity->dto is not implemented")
    }

    fun toResponse(dto: D): S {
        throw UnsupportedOperationException("dto->response is not implemented")
    }

}

interface IEntity
interface IDto
interface IResponse
interface IRequest
