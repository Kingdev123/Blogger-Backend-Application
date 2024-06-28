package com.blogapplication.Payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDTO> content;
    private Integer pageNumber;
    private Integer PageSize;
    private Long totalElements;
    private Integer totalPages;

    private boolean isLastPage;

    
}
