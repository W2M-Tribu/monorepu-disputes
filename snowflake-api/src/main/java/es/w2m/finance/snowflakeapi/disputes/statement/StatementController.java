package es.w2m.finance.snowflakeapi.disputes.statement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class StatementController {

    private final StatementService statementService;

    @PreAuthorize("hasRole('disputes')")
    @GetMapping("/test-snowflake")
    public ResponseEntity<StatementResponse> testQuery() {
        return ResponseEntity.ok(this.statementService.executeTestQuery());
    }
}