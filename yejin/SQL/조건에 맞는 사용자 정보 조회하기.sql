select user.user_id, user.nickname, concat(city,' ',street_address1,' ',street_address2) as 전체주소, concat(substring(tlno from 1 for 3),'-',substring(tlno from 4 for 4),'-',substring(tlno from 8 for 4)) as 전화번호
from USED_GOODS_BOARD as board
        inner join USED_GOODS_USER as user
        on board.writer_id = user.user_id
group by user.user_id
having count(*)>=3
order by user.user_id desc;
