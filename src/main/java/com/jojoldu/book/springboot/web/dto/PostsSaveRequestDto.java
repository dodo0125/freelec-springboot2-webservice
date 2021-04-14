package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    //Dto 클래스 추가로 생성. Dto클래스는 View를 위한 클래스라 자주 변경됨.
    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //Entity 클래스 생성. 데이터베이스와 맞닿은 핵심 클래스로 Entity 클래스를 기준으로 테이블 생성 & 스키마 변경됨
    //절대로 Entity 클래스를 Request/Response 클래스로 사용하면 안됨.
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
