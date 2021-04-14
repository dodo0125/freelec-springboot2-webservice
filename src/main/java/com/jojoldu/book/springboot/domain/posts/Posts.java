package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 롬복의 코드를 단순화 시켜주지만 필수는 아님. 필수 어노테이션일수록 클래스와 가까이둠
@Getter //롬복의 어노테이션, 클래스 내 모든 필드의 Getter 메소드를 자동 생성해줌
@NoArgsConstructor //롬복의 어노테이션, 기본 생성자 자동추가, public Posts(){}와 같은 역할
@Entity //JPA의 어노테이션, 테이블과 링크될 클래스, Entity 클래스에선 절대 Setter 메소드 만들지 않음.

//Posts 클래스는 실제 DB table과 매칭될 클래스.(Entity 클래스라 지칭함)
//JPA를 사용하면 DB데이터에 작업할 경우, 실제 쿼리를 날리기보단 이 Entity 클래스의 수정을 통해 작업함.
public class Posts extends BaseTimeEntity {
    // @Id : table의 OK 필드
    @Id
    //@GeneratedValue : PK의 생성 규칙 나타냄, GenerationType.IDENTITY 옵션: auto_increment 해줌.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column : 테이블의 컬럼을 나타내며, 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됨.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // @Builder : 롬복의 어노테이션, 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author){
        this.title = title ;
        this.content = content ;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
