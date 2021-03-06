package com.openmarket.bct.web.rest;
import com.openmarket.bct.service.FeeService;
import com.openmarket.bct.web.rest.errors.BadRequestAlertException;
import com.openmarket.bct.web.rest.util.HeaderUtil;
import com.openmarket.bct.web.rest.util.PaginationUtil;
import com.openmarket.bct.service.dto.FeeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Fee.
 */
@RestController
@RequestMapping("/api")
public class FeeResource {

    private final Logger log = LoggerFactory.getLogger(FeeResource.class);

    private static final String ENTITY_NAME = "fee";

    private final FeeService feeService;

    public FeeResource(FeeService feeService) {
        this.feeService = feeService;
    }

    /**
     * POST  /fees : Create a new fee.
     *
     * @param feeDTO the feeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feeDTO, or with status 400 (Bad Request) if the fee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fees")
    public ResponseEntity<FeeDTO> createFee(@RequestBody FeeDTO feeDTO) throws URISyntaxException {
        log.debug("REST request to save Fee : {}", feeDTO);
        if (feeDTO.getId() != null) {
            throw new BadRequestAlertException("A new fee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeeDTO result = feeService.save(feeDTO);
        return ResponseEntity.created(new URI("/api/fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fees : Updates an existing fee.
     *
     * @param feeDTO the feeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated feeDTO,
     * or with status 400 (Bad Request) if the feeDTO is not valid,
     * or with status 500 (Internal Server Error) if the feeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fees")
    public ResponseEntity<FeeDTO> updateFee(@RequestBody FeeDTO feeDTO) throws URISyntaxException {
        log.debug("REST request to update Fee : {}", feeDTO);
        if (feeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeeDTO result = feeService.save(feeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, feeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fees : get all the fees.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fees in body
     */
    @GetMapping("/fees")
    public ResponseEntity<List<FeeDTO>> getAllFees(Pageable pageable) {
        log.debug("REST request to get a page of Fees");
        Page<FeeDTO> page = feeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fees");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /fees/:id : get the "id" fee.
     *
     * @param id the id of the feeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fees/{id}")
    public ResponseEntity<FeeDTO> getFee(@PathVariable Long id) {
        log.debug("REST request to get Fee : {}", id);
        Optional<FeeDTO> feeDTO = feeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feeDTO);
    }

    /**
     * DELETE  /fees/:id : delete the "id" fee.
     *
     * @param id the id of the feeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fees/{id}")
    public ResponseEntity<Void> deleteFee(@PathVariable Long id) {
        log.debug("REST request to delete Fee : {}", id);
        feeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
