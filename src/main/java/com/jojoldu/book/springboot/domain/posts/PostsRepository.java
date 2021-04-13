// Posts 클래스로 Database 접근하게 해줄 JpaRepository를 생성
// MyBatis에서 Dao라고 불리는 DB Layer접근자로, JPA에선 Repository라 불리며 인터페이스로 생성함.
// Entity 클래스와 기본 Repository는 함께 움직여야하므로 도메인 PKG에서 함께 관리해야함.
package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// extends ~<,> : <Entity 클래스명, PK 타입> 상속
public interface PostsRepository extends JpaRepository<Posts,Long>{

}
