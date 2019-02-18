package com.openmarket.bct.web.rest;

import com.openmarket.bct.BctApp;

import com.openmarket.bct.domain.Fee;
import com.openmarket.bct.repository.FeeRepository;
import com.openmarket.bct.service.FeeService;
import com.openmarket.bct.service.dto.FeeDTO;
import com.openmarket.bct.service.mapper.FeeMapper;
import com.openmarket.bct.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.openmarket.bct.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FeeResource REST controller.
 *
 * @see FeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BctApp.class)
public class FeeResourceIntTest {

    private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNIT_PRICE = new BigDecimal(2);

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final BigDecimal DEFAULT_SUB_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUB_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TAX_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAX_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TAX_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAX_PERCENTAGE = new BigDecimal(2);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_NOTES = "BBBBBBBBBB";

    private static final Integer DEFAULT_APPEARANCE_ORDER = 1;
    private static final Integer UPDATED_APPEARANCE_ORDER = 2;

    private static final String DEFAULT_COST_ID = "AAAAAAAAAA";
    private static final String UPDATED_COST_ID = "BBBBBBBBBB";

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private FeeMapper feeMapper;

    @Autowired
    private FeeService feeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFeeMockMvc;

    private Fee fee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeeResource feeResource = new FeeResource(feeService);
        this.restFeeMockMvc = MockMvcBuilders.standaloneSetup(feeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fee createEntity(EntityManager em) {
        Fee fee = new Fee()
            .unitPrice(DEFAULT_UNIT_PRICE)
            .quantity(DEFAULT_QUANTITY)
            .subTotal(DEFAULT_SUB_TOTAL)
            .taxTotal(DEFAULT_TAX_TOTAL)
            .taxPercentage(DEFAULT_TAX_PERCENTAGE)
            .description(DEFAULT_DESCRIPTION)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY)
            .internalNotes(DEFAULT_INTERNAL_NOTES)
            .appearanceOrder(DEFAULT_APPEARANCE_ORDER)
            .costId(DEFAULT_COST_ID);
        return fee;
    }

    @Before
    public void initTest() {
        fee = createEntity(em);
    }

    @Test
    @Transactional
    public void createFee() throws Exception {
        int databaseSizeBeforeCreate = feeRepository.findAll().size();

        // Create the Fee
        FeeDTO feeDTO = feeMapper.toDto(fee);
        restFeeMockMvc.perform(post("/api/fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDTO)))
            .andExpect(status().isCreated());

        // Validate the Fee in the database
        List<Fee> feeList = feeRepository.findAll();
        assertThat(feeList).hasSize(databaseSizeBeforeCreate + 1);
        Fee testFee = feeList.get(feeList.size() - 1);
        assertThat(testFee.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testFee.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testFee.getSubTotal()).isEqualTo(DEFAULT_SUB_TOTAL);
        assertThat(testFee.getTaxTotal()).isEqualTo(DEFAULT_TAX_TOTAL);
        assertThat(testFee.getTaxPercentage()).isEqualTo(DEFAULT_TAX_PERCENTAGE);
        assertThat(testFee.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFee.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFee.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testFee.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFee.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testFee.getInternalNotes()).isEqualTo(DEFAULT_INTERNAL_NOTES);
        assertThat(testFee.getAppearanceOrder()).isEqualTo(DEFAULT_APPEARANCE_ORDER);
        assertThat(testFee.getCostId()).isEqualTo(DEFAULT_COST_ID);
    }

    @Test
    @Transactional
    public void createFeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = feeRepository.findAll().size();

        // Create the Fee with an existing ID
        fee.setId(1L);
        FeeDTO feeDTO = feeMapper.toDto(fee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeeMockMvc.perform(post("/api/fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fee in the database
        List<Fee> feeList = feeRepository.findAll();
        assertThat(feeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFees() throws Exception {
        // Initialize the database
        feeRepository.saveAndFlush(fee);

        // Get all the feeList
        restFeeMockMvc.perform(get("/api/fees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fee.getId().intValue())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].subTotal").value(hasItem(DEFAULT_SUB_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].taxTotal").value(hasItem(DEFAULT_TAX_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].taxPercentage").value(hasItem(DEFAULT_TAX_PERCENTAGE.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].internalNotes").value(hasItem(DEFAULT_INTERNAL_NOTES.toString())))
            .andExpect(jsonPath("$.[*].appearanceOrder").value(hasItem(DEFAULT_APPEARANCE_ORDER)))
            .andExpect(jsonPath("$.[*].costId").value(hasItem(DEFAULT_COST_ID.toString())));
    }
    
    @Test
    @Transactional
    public void getFee() throws Exception {
        // Initialize the database
        feeRepository.saveAndFlush(fee);

        // Get the fee
        restFeeMockMvc.perform(get("/api/fees/{id}", fee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fee.getId().intValue()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.subTotal").value(DEFAULT_SUB_TOTAL.intValue()))
            .andExpect(jsonPath("$.taxTotal").value(DEFAULT_TAX_TOTAL.intValue()))
            .andExpect(jsonPath("$.taxPercentage").value(DEFAULT_TAX_PERCENTAGE.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.internalNotes").value(DEFAULT_INTERNAL_NOTES.toString()))
            .andExpect(jsonPath("$.appearanceOrder").value(DEFAULT_APPEARANCE_ORDER))
            .andExpect(jsonPath("$.costId").value(DEFAULT_COST_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFee() throws Exception {
        // Get the fee
        restFeeMockMvc.perform(get("/api/fees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFee() throws Exception {
        // Initialize the database
        feeRepository.saveAndFlush(fee);

        int databaseSizeBeforeUpdate = feeRepository.findAll().size();

        // Update the fee
        Fee updatedFee = feeRepository.findById(fee.getId()).get();
        // Disconnect from session so that the updates on updatedFee are not directly saved in db
        em.detach(updatedFee);
        updatedFee
            .unitPrice(UPDATED_UNIT_PRICE)
            .quantity(UPDATED_QUANTITY)
            .subTotal(UPDATED_SUB_TOTAL)
            .taxTotal(UPDATED_TAX_TOTAL)
            .taxPercentage(UPDATED_TAX_PERCENTAGE)
            .description(UPDATED_DESCRIPTION)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY)
            .internalNotes(UPDATED_INTERNAL_NOTES)
            .appearanceOrder(UPDATED_APPEARANCE_ORDER)
            .costId(UPDATED_COST_ID);
        FeeDTO feeDTO = feeMapper.toDto(updatedFee);

        restFeeMockMvc.perform(put("/api/fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDTO)))
            .andExpect(status().isOk());

        // Validate the Fee in the database
        List<Fee> feeList = feeRepository.findAll();
        assertThat(feeList).hasSize(databaseSizeBeforeUpdate);
        Fee testFee = feeList.get(feeList.size() - 1);
        assertThat(testFee.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testFee.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testFee.getSubTotal()).isEqualTo(UPDATED_SUB_TOTAL);
        assertThat(testFee.getTaxTotal()).isEqualTo(UPDATED_TAX_TOTAL);
        assertThat(testFee.getTaxPercentage()).isEqualTo(UPDATED_TAX_PERCENTAGE);
        assertThat(testFee.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFee.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFee.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testFee.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFee.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testFee.getInternalNotes()).isEqualTo(UPDATED_INTERNAL_NOTES);
        assertThat(testFee.getAppearanceOrder()).isEqualTo(UPDATED_APPEARANCE_ORDER);
        assertThat(testFee.getCostId()).isEqualTo(UPDATED_COST_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingFee() throws Exception {
        int databaseSizeBeforeUpdate = feeRepository.findAll().size();

        // Create the Fee
        FeeDTO feeDTO = feeMapper.toDto(fee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeeMockMvc.perform(put("/api/fees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fee in the database
        List<Fee> feeList = feeRepository.findAll();
        assertThat(feeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFee() throws Exception {
        // Initialize the database
        feeRepository.saveAndFlush(fee);

        int databaseSizeBeforeDelete = feeRepository.findAll().size();

        // Delete the fee
        restFeeMockMvc.perform(delete("/api/fees/{id}", fee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fee> feeList = feeRepository.findAll();
        assertThat(feeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fee.class);
        Fee fee1 = new Fee();
        fee1.setId(1L);
        Fee fee2 = new Fee();
        fee2.setId(fee1.getId());
        assertThat(fee1).isEqualTo(fee2);
        fee2.setId(2L);
        assertThat(fee1).isNotEqualTo(fee2);
        fee1.setId(null);
        assertThat(fee1).isNotEqualTo(fee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeeDTO.class);
        FeeDTO feeDTO1 = new FeeDTO();
        feeDTO1.setId(1L);
        FeeDTO feeDTO2 = new FeeDTO();
        assertThat(feeDTO1).isNotEqualTo(feeDTO2);
        feeDTO2.setId(feeDTO1.getId());
        assertThat(feeDTO1).isEqualTo(feeDTO2);
        feeDTO2.setId(2L);
        assertThat(feeDTO1).isNotEqualTo(feeDTO2);
        feeDTO1.setId(null);
        assertThat(feeDTO1).isNotEqualTo(feeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(feeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(feeMapper.fromId(null)).isNull();
    }
}
