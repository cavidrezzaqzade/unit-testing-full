package az.ingress.unittesting2.controller

import az.ingress.unittesting2.exception.ErrorHandler
import az.ingress.unittesting2.model.UserDto
import az.ingress.unittesting2.service.UserService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals

class UserControllerTest extends Specification{
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private UserService service
    private MockMvc mockMvc

    void setup() {
        service = Mock()
        def controller = new UserController(service)
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestGetById success case"() {
        given:
        def id = random.nextObject(Long)
        def url = "/v1/users/$id"

        def actualResponse = new UserDto(1, "Javid", 28.shortValue())

        def exceptedResponse =
                                        """
                                            {
                                                "id" : 1,
                                                "name" : "Javid",
                                                "age" : 28
                                            }
                                        """

        when:
        def result = mockMvc.perform(
            get(url)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn()

        then:
        1 * service.getById(id) >> actualResponse

        result.response.status == HttpStatus.OK.value()
        assertEquals(exceptedResponse, result.response.contentAsString, true)

    }

}
