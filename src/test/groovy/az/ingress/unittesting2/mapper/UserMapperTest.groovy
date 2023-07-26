package az.ingress.unittesting2.mapper

import az.ingress.unittesting2.dao.entity.User
import az.ingress.unittesting2.model.UserDto
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.mapstruct.factory.Mappers
import spock.lang.Specification

/**
 * @author caci
 */

class UserMapperTest extends Specification{

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private UserMapper mapper = Mappers.getMapper(UserMapper.class)

    def "TestEntityToDto success case"() {
        given:
        def entity = random.nextObject(User)

        when:
        def actual = mapper.entityToDto(entity)

        then:
        actual.id == entity.id
        actual.name == entity.name
        actual.age == entity.age
    }

    def "TestDtoToEntity  success case"() {
        given:
        def dto = random.nextObject(UserDto)

        when:
        def actual = mapper.dtoToEntity(dto)

        then:
        actual.id == dto.id
        actual.name == dto.name
        actual.age == dto.age
    }

}
