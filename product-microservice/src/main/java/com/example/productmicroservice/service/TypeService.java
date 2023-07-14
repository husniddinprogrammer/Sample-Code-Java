package com.example.productmicroservice.service;

import com.example.productmicroservice.dto.type.get.TypeGetResponseDto;
import com.example.productmicroservice.dto.type.update.TypeUpdateRequestDto;
import com.example.productmicroservice.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * This interface represents a service for managing Type entities.
 */
@Service
public interface TypeService {
    /**
     * Creates a new Type.
     *
     * @param type The Type object to create.
     */
    void createType(Type type);

    /**
     * Deletes a Type by its id.
     *
     * @param id The id of the Type to delete.
     */
    void deleteType(Long id);

    /**
     * Updates a Type with new information.
     *
     * @param id                    The id of the Detail to update.
     * @param typeUpdateRequestDto  The TypeUpdateRequestDto containing the updated information.
     * @return The updated Type object.
     */
    Type changeType(Long id,
                      TypeUpdateRequestDto typeUpdateRequestDto);

    /**
     * Retrieves all Types with pagination.
     *
     * @param page   The page number.
     * @param size   The number of items per page.
     * @param sortBy The field to sort the results by.
     * @return A Page containing the TypeGetResponseDto objects.
     */
    Page<TypeGetResponseDto> getAllTypes(int page,
                                          int size,
                                          String sortBy);

    /**
     * Searches for Types by name with pagination.
     *
     * @param name   The name to search for.
     * @param page   The page number.
     * @param size   The number of items per page.
     * @param sortBy The field to sort the results by.
     * @return A Page containing the TypeGetResponseDto objects.
     */
    Page<TypeGetResponseDto> searchTypesByName(String name,
                                                    int page,
                                                    int size,
                                                    String sortBy);

}
