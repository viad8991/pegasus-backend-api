package org.pegasus.backendapi.utils

interface IMapper<R : IRequest, D : IDto, E : IEntity, S : IResponse> {

    // Controller -> (Contr + Service + Repo) -> (Repository) -> (Repo + Service + Contr) -> Controller
    // FooRequest ->           FooDto         ->  FooEntity   ->          FooDto          -> FooResponse

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
