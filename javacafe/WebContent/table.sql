drop TABLE members purge;
CREATE TABLE members(user_no VARCHAR2(20)
                    ,password VARCHAR2(20)
                    ,NAME VARCHAR2(50)
                    ,email VARCHAR2(50)
                    ,phone VARCHAR2(20)
                    ,address1 VARCHAR2(50)
                    ,address2 VARCHAR2(50)
                    ,birth DATE
                    ,reg_date DATE
                    ,out_date DATE
                    ,grade VARCHAR2(10));

UPDATE members
SET    grade = 'R'
WHERE  user_no = 'admin';

drop TABLE goods purge;
CREATE TABLE goods(prod_no VARCHAR2(10)
                  ,prod_name VARCHAR2(50)
                  ,prod_content VARCHAR2(100)
                  ,onhand_qty NUMBER
                  ,prod_price NUMBER
                  ,off_price NUMBER
                  ,prod_category VARCHAR2(10)
                  ,useyn VARCHAR2(10)
                  ,prod_image VARCHAR2(30));

drop TABLE category purge;
CREATE TABLE category(category_id VARCHAR2(10)
                     ,category_name VARCHAR2(50)
                     ,category_desc VARCHAR2(300));

CREATE TABLE boards(bbsnum VARCHAR2(50)
                   ,title VARCHAR2(500)
                   ,contents VARCHAR2(3000)
                   ,REF VARCHAR2(3000)
                   ,re_step VARCHAR2(10)
                   ,reg_date DATE
                   ,readcount NUMBER
                   ,password_yn VARCHAR2(10)
                   ,ref_lev VARCHAR2(10)
                   ,user_no VARCHAR2(50)
                   ,prod_no VARCHAR2(10));
