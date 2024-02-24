package com.example.demo.token;

//public interface TokenRepository extends JpaRepository<Token, Integer> {

//	  @Query(value = """
//	      select t from Token t inner join User u\s
//	      on t.user.id = u.id\s
//	      where u.id = :id and (t.expired = false or t.revoked = false)\s
//	      """)
//	  List<Token> findAllValidTokenByUser(Integer id);
//
//	  Optional<Token> findByToken(String token);
//	}