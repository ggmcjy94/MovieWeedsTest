insert into genre (name) values ('SF');
insert into genre (name) values ('TV영화');
insert into genre (name) values ('가족');
insert into genre (name) values ('공포');
insert into genre (name) values ('다큐멘터리');
insert into genre (name) values ('드라마');
insert into genre (name) values ('로맨스');
insert into genre (name) values ('모험');
insert into genre (name) values ('미스터리');
insert into genre (name) values ('범죄');
insert into genre (name) values ('서부');
insert into genre (name) values ('스릴러');
insert into genre (name) values ('애니메이션');
insert into genre (name) values ('액션');
insert into genre (name) values ('역사');
insert into genre (name) values ('음악');
insert into genre (name) values ('전쟁');
insert into genre (name) values ('코미디');
insert into genre (name) values ('판타지');


insert into movie
(title,language,overview,popularity,grade,grade_count,poster_path,release_date,status,runtime,budget,revenue)
values ('오펀: 천사의탄생', 'en' ,
'엄청난 비밀을 숨긴 사이코패스가 에스토니아의 정신병동을 탈출,\r 부유한 가족의 실종된 딸 ‘에스더’로 사칭해 미국에 온다.\r 재회의 기쁨도 잠시, 어딘지 낯선 딸의 정체를 눈치챈 엄마는\r 가족을 지키기 위해 에스더와 맞서는데...  누구도 상상 못한 충격적인 반전이 기다린다!',
 1, 0,0, '/vKIhsEVEtLTwTkmLSDNi230Zr3Q.jpg','2022-07-27','Released',99,0,9572765 );

insert into movie
(title,language,overview,popularity,grade,grade_count,poster_path,release_date,status,runtime,budget,revenue)
values ('호커스 포커스 2', 'en' ,
'누군가가 검은 불꽃 초에 불을 붙여 17세기에 활동했던 자매들을 되살린 지도 29년이 흘렀다. 이제 자매들은 원수를 갚으려 한다. 핼러윈 전날의 동이 터오기 전에 세일럼에서 대혼란을 일으키려는 마녀들을 막아설 사람은 세 고등학생뿐이다.',
2, 0,0, '/xpDdvIaTHn38F17pPseL1MQI05B.jpg','2022-09-27','Released',105,0,0);

insert into movie
(title,language,overview,popularity,grade,grade_count,poster_path,release_date,status,runtime,budget,revenue)
values ('노아의 방주', 'en' ,
'땅돼지 길버트는 모든 동물을 모아서 노아의 방주에 싣는 일을 맡게 되는데...',
3, 0,0, '/ur5DYpWrfhgirnSXi82SLg1qHOq.jpg','2023-04-10','In Production',118,35000000,0);


insert into genre_movie (movie_id, genre_id) values (1, 4);
insert into genre_movie (movie_id, genre_id) values (1, 12);

insert into genre_movie (movie_id, genre_id) values (2, 3);
insert into genre_movie (movie_id, genre_id) values (2, 18);
insert into genre_movie (movie_id, genre_id) values (2, 19);

insert into genre_movie (movie_id, genre_id) values (3, 3);
insert into genre_movie (movie_id, genre_id) values (3, 13);

