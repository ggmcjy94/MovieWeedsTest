insert into genre (name) values ('SF'); --1
insert into genre (name) values ('TV영화'); --2
insert into genre (name) values ('가족'); -- 3
insert into genre (name) values ('공포');--4
insert into genre (name) values ('다큐멘터리');--5
insert into genre (name) values ('드라마');--6
insert into genre (name) values ('로맨스');--7
insert into genre (name) values ('모험');--8
insert into genre (name) values ('미스터리');--9
insert into genre (name) values ('범죄');--10
insert into genre (name) values ('서부');--11
insert into genre (name) values ('스릴러');--12
insert into genre (name) values ('애니메이션');--13
insert into genre (name) values ('액션');--14
insert into genre (name) values ('역사');--15
insert into genre (name) values ('음악');--16
insert into genre (name) values ('전쟁');--17
insert into genre (name) values ('코미디');--18
insert into genre (name) values ('판타지');--19


insert into movie
(title,language,overview,popularity,grade,grade_count,poster_path,release_date,runtime,budget,revenue)
values ('오펀: 천사의탄생', 'en' ,
'엄청난 비밀을 숨긴 사이코패스가 에스토니아의 정신병동을 탈출,\r 부유한 가족의 실종된 딸 ‘에스더’로 사칭해 미국에 온다.\r 재회의 기쁨도 잠시, 어딘지 낯선 딸의 정체를 눈치챈 엄마는\r 가족을 지키기 위해 에스더와 맞서는데...  누구도 상상 못한 충격적인 반전이 기다린다!',
 1, 0,0, '/vKIhsEVEtLTwTkmLSDNi230Zr3Q.jpg','2022-07-27',99,0,9572765 );

insert into movie
(title,language,overview,popularity,grade,grade_count,poster_path,release_date,runtime,budget,revenue)
values ('호커스 포커스 2', 'en' ,
'누군가가 검은 불꽃 초에 불을 붙여 17세기에 활동했던 자매들을 되살린 지도 29년이 흘렀다. 이제 자매들은 원수를 갚으려 한다. 핼러윈 전날의 동이 터오기 전에 세일럼에서 대혼란을 일으키려는 마녀들을 막아설 사람은 세 고등학생뿐이다.',
2, 0,0, '/xpDdvIaTHn38F17pPseL1MQI05B.jpg','2022-09-27',105,0,0);

insert into movie
(title,language,overview,popularity,grade,grade_count,poster_path,release_date,runtime,budget,revenue)
values ('노아의 방주', 'en' ,
'땅돼지 길버트는 모든 동물을 모아서 노아의 방주에 싣는 일을 맡게 되는데...',
3, 0,0, '/ur5DYpWrfhgirnSXi82SLg1qHOq.jpg','2023-04-10',118,35000000,0);

insert into movie
(title,language,overview,popularity,grade,grade_count,poster_path,release_date,runtime,budget,revenue)
values ('블랙 아담', 'en' ,
'5000년 전 고대 도시 ‘칸다크’의 노예에서 그 누구도 막을 수 없는 불사신으로 깨어난 ‘블랙 아담’이 자신만의 방식으로 정의를 구현하기 위해 일격에 나서는 이야기를 그린 액션 블록버스터',
0, 0, 0, '/mEdMHGy1FfCUc7PskFO0tibm8jp.jpg','2022-10-19',125,185000000,5000000);

insert into movie
(title,language,overview,popularity,grade,grade_count,poster_path,release_date,runtime,budget,revenue)
values ('프레이 포 더 데블', 'en' ,
'과거의 트라우마로 여전히 고통 받고 있는 수녀 앤  구마 의식 학교에서 특별한 재능을 인정받으며\r 남성 사제에게만 허용되었던 구마 의식 훈련을 받게 되고  그곳에서 만난 환자들을 통해\r 어릴 적부터 그녀를 괴롭혀 왔던 존재의 실체와 만나게 되는데...',
0, 0, 0, '/zNFUrnycnAFp7xukctFg1Ey7YBH.jpg','2022-10-26',93,0,0);


insert into genre_movie (movie_id, genre_id) values (1, 4);
insert into genre_movie (movie_id, genre_id) values (1, 12);

insert into genre_movie (movie_id, genre_id) values (2, 3);
insert into genre_movie (movie_id, genre_id) values (2, 18);
insert into genre_movie (movie_id, genre_id) values (2, 19);

insert into genre_movie (movie_id, genre_id) values (3, 3);
insert into genre_movie (movie_id, genre_id) values (3, 13);

insert into genre_movie (movie_id, genre_id) values (4, 14);
insert into genre_movie (movie_id, genre_id) values (4, 19);
insert into genre_movie (movie_id, genre_id) values (4, 8);

insert into genre_movie (movie_id, genre_id) values (5, 4);
insert into genre_movie (movie_id, genre_id) values (5, 12);





