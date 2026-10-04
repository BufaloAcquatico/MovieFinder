package it.lentini.moviefinder.dto.mapper;

import it.lentini.moviefinder.domain.Review;
import it.lentini.moviefinder.dto.request.CreateReviewRequest;
import it.lentini.moviefinder.dto.request.UpdateReviewRequest;
import it.lentini.moviefinder.dto.response.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, MovieMapper.class})
public interface ReviewMapper {
    ReviewResponse toResponse(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "movie", ignore = true)
    Review toEntity(CreateReviewRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "movie", ignore = true)
    void updateEntityFromRequest(UpdateReviewRequest request, @MappingTarget Review review);
}
