package io.alxndr.demoexceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexander
 * @date 2020-11-12
 **/
@RestController
public class BoardController {

    @GetMapping("/board")
    public ResponseEntity<String> board() {
        throw new NotFoundException(ErrorCode.BOARD_NOT_FOUND.getErrorCode(), ErrorCode.BOARD_NOT_FOUND.getErrorMessage());
    }
}
