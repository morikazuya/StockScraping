
/* ユーザーマスタのデータ（アドミン権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('yamada@xxx.co.jp', '$2a$10$nfkPHEx/beRJCNZQVYdoSuGE2M0nD6e.fcg.BeJbFZ2teQqQpcEuy', '山田太郎', '1990-01-01', 28, false, 'ROLE_ADMIN');

/* ユーザーマスタのデータ（一般権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('tamura@xxx.co.jp', '$2a$10$nfkPHEx/beRJCNZQVYdoSuGE2M0nD6e.fcg.BeJbFZ2teQqQpcEuy', '田村達也', '1986-11-05', 31, false, 'ROLE_GENERAL');

/* ユーザーマスタのデータ（アドミン権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('mori@xxx.co.jp', '$2a$10$nfkPHEx/beRJCNZQVYdoSuGE2M0nD6e.fcg.BeJbFZ2teQqQpcEuy', '森和也', '1994-06-24', 25, false, 'ROLE_ADMIN');
