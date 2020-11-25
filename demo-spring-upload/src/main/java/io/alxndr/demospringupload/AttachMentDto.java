package io.alxndr.demospringupload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttachMentDto {

    private String originalFileName;

    private String savedFileName;

    private String filePath;

}
