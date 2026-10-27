package com.investment.portfolio.infrastructure.adapter.in.web;

import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.port.in.InvestorRequestPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador REST que expone endpoints para las operaciones de consulta,
 * inversión y retiro de fondos.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/investor-requests")
@RequiredArgsConstructor
@Tag(name = "Investor Requests", description = "API para gestionar solicitudes de inversores")
public class InvestorRequestController {

    private final InvestorRequestPort investorRequestPort;

    @PostMapping
    @Operation(summary = "Procesar solicitud de inversor",
            description = "Procesa una solicitud de inversión, consulta o retiro de fondos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud procesada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflicto de idempotencia",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvestorRequest> processInvestorRequest(
            @Valid @RequestBody InvestorRequest request) {
        log.info("Recibida solicitud de tipo: {}, idempotencyKey: {}",
                request.operationType(), request.idempotencyKey());

        InvestorRequest processedRequest = investorRequestPort.processInvestorRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(processedRequest);
    }

    @GetMapping("/{requestId}")
    @Operation(summary = "Consultar solicitud por ID",
            description = "Obtiene una solicitud de inversor por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvestorRequest> getInvestorRequestById(
            @PathVariable UUID requestId) {
        log.info("Consultando solicitud con id: {}", requestId);
        InvestorRequest request = investorRequestPort.getInvestorRequestById(requestId);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/idempotency/{idempotencyKey}")
    @Operation(summary = "Consultar solicitud por clave de idempotencia",
            description = "Obtiene una solicitud de inversor por su clave de idempotencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvestorRequest> getInvestorRequestByIdempotencyKey(
            @PathVariable String idempotencyKey) {
        log.info("Consultando solicitud con clave de idempotencia: {}", idempotencyKey);
        InvestorRequest request = investorRequestPort.getInvestorRequestByIdempotencyKey(idempotencyKey);
        return ResponseEntity.ok(request);
    }

    @Schema(description = "Respuesta de error estándar")
    public record ErrorResponse(
            @Schema(description = "Código de error", example = "INVALID_REQUEST")
            String errorCode,
            @Schema(description = "Mensaje de error", example = "La solicitud es inválida")
            String message,
            @Schema(description = "Ruta de la solicitud que causó el error")
            String path) {
    }
}