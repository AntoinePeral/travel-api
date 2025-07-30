package com.wcs.travel_blog.step.mapper;

import com.wcs.travel_blog.step.dto.StepDTo;
import com.wcs.travel_blog.step.model.Step;
import com.wcs.travel_blog.theme.mapper.ThemeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StepMapper {


    private final ThemeMapper themeMapper;

    public StepMapper(ThemeMapper themeMapper) {
        this.themeMapper = themeMapper;
    }
    public StepDTo toDto(Step step) {
        StepDTo stepDto = new StepDTo();
        stepDto.setTitle(step.getTitle());
        stepDto.setDescription(step.getDescription());
        stepDto.setCreatedAt(step.getCreatedAt());
        stepDto.setUpdatedAt(step.getUpdatedAt());
        stepDto.setStartDate(step.getStartDate());
        stepDto.setEndDate(step.getEndDate());
        stepDto.setStatus(step.getStatus());
        stepDto.setLatitude(step.getLatitude());
        stepDto.setLongitude(step.getLongitude());
        stepDto.setCity(step.getCity());
        stepDto.setCountry(step.getCountry());
        stepDto.setContinent(step.getContinent());

        if (step.getTravelDiary().getId() != null) {
            stepDto.setTravelDiaryId(step.getTravelDiary().getId());
        }
        stepDto.setThemes(step.getThemes() != null
        ? step.getThemes().stream()
                .map(themeMapper::toDto)
                .collect(Collectors.toList())
                : List.of());
        return stepDto;
    }
}