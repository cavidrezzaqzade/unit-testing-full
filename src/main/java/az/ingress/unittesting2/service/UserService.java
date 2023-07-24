package az.ingress.unittesting2.service;

import az.ingress.unittesting2.dao.entity.User;
import az.ingress.unittesting2.dao.repository.UserRepository;
import az.ingress.unittesting2.exception.ApplicationException;
import az.ingress.unittesting2.mapper.UserMapper;
import az.ingress.unittesting2.model.UserDetails;
import az.ingress.unittesting2.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

import static az.ingress.unittesting2.exception.Errors.DATA_NOT_FOUND;


/**
 * @author caci
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @SneakyThrows
    public List<UserDto> getAll() {
        List<User> users = repository.findAll();
        return mapper.entitiesToDtos(users);
    }

    public UserDto add(UserDto userDto) {
        User user = mapper.dtoToEntity(userDto);
        return mapper.entityToDto(repository.save(user));
    }

    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ApplicationException(DATA_NOT_FOUND, Map.of("id", id)));
        repository.delete(user);
    }

    public UserDto update(UserDto dto, Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ApplicationException(DATA_NOT_FOUND, Map.of("id", id)));
        User userNew = mapper.updateUser(user, dto);
        return mapper.entityToDto(repository.save(userNew));
    }

    public UserDto update(UserDetails userDetails) {
        User user = repository.findById(userDetails.getId()).orElseThrow(() -> new ApplicationException(DATA_NOT_FOUND, Map.of("id", userDetails.getId())));

        user.setAge(userDetails.getAge());
        user.setName(userDetails.getName());

        return mapper.entityToDto(repository.save(user));
    }

}
