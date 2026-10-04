package it.lentini.moviefinder.dto.mapper;

import it.lentini.moviefinder.domain.User;
import it.lentini.moviefinder.dto.response.UserSummaryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserSummaryResponse toSummaryResponse(User user);
}
