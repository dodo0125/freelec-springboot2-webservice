// test 클래스에서는 save, findAll 기능 test
package com.jojoldu.book.springboot.domain.posts;

// import org.junit.After; jUnit 5부터는 아래 import 패키지로 대체
import org.junit.jupiter.api.AfterEach;
// import org.junit.Test ; jUnit 5부터는 아래 import 패키지로 대체
import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith ; jUnit 5부터는 아래 import 패키지로 대체
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest ;
//import org.springframework.test.context.junit4.SpringRunner;  ; jUnit 5부터는 아래 import 패키지로 대체
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) //junit4에서는 @RunWith(SpringRunner.class)
//별다른 설정없이 @SpringBootTest를 사용할 경우, H2 데이터베이스를 자동으로 실행해줌
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // Junit에서는 단위테스트가 끝날 때마다 수행되는 메소드를 지정,
    // 테스트용 db인 h2에 데이터가 그대로 남아 있어 다음 테스트 실행시 테스트가 실패할 수 있음
    @AfterEach // junit4에서는 @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테이블 posts에 insert/update 쿼리 실행. id값이 있으면 update, 없으면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("jojoldu@gmail.com")
                        .build()) ;

        //when
        //postsRepository.findAll() : 테이블 posts에 있는 모든 데이터들을 조회해 오는 메소드
        List<Posts> postsList = postsRepository.findAll() ;

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);


    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>> create Date = " +posts.getCreatedDate() + ", modifiedDtae = " +posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}

