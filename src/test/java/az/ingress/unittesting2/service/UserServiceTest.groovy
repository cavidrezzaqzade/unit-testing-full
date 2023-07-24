package az.ingress.unittesting2.service

import az.ingress.unittesting2.dao.entity.User
import az.ingress.unittesting2.dao.repository.UserRepository
import az.ingress.unittesting2.mapper.UserMapper
import az.ingress.unittesting2.model.UserDto
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class UserServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom();
    private UserRepository repository;
    private UserService service;
    private UserMapper mapper;

//    def setup() {
//        repository = Mock()
//        mapper = Mock()
//        service = new UserService(repository, mapper)
//    }

    def "TestGetById success case"() {
        given:
        def id = random.nextObject(Long)
        def user = random.nextObject(User)
        def userDto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .build()

        when:
        def userDtoResponse = service.getById(id)

        then:
        1 * repository.findById(id) >> Optional.of(user)
        1 * mapper.entityToDto(user) >> userDto
        userDtoResponse.id == user.id
        userDtoResponse.age == user.age
        userDtoResponse.name == user.name
    }

    def "TestGetById user not found case"() {
        given:
        def id = random.nextObject(Long)

        when:
        service.getById(id)

        then:
        1 * repository.findById(id)>> Optional.empty()
        RuntimeException ex = thrown()
        ex.getMessage() == "user was not found"
    }
}
