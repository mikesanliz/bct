package com.openmarket.bct.service;

import com.openmarket.bct.service.dto.FeeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Fee.
 */
public interface FeeService {

    /**
     * Save a fee.
     *
     * @param feeDTO the entity to save
     * @return the persisted entity
     */
    FeeDTO save(FeeDTO feeDTO);

    /**
     * Get all the fees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FeeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FeeDTO> findOne(Long id);

    /**
     * Delete the "id" fee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
