-- 코드를 입력하세요
SELECT USER_ID, NICKNAME, concat(CITY, ' ',STREET_ADDRESS1,' ',STREET_ADDRESS2) 전체주소, 
concat(substring(tlno from 1 for 3),'-',substring(tlno from 4 for 4),'-',substring(tlno from 8 for 4)) 전화번호
from USED_GOODS_USER
where user_id in(select WRITER_ID from USED_GOODS_BOARD group by WRITER_ID having count(board_id)>=3)
order by user_id desc;
