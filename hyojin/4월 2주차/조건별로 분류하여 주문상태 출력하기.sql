select USER_ID, NICKNAME, sum(PRICE) TOTAL_SALES
from used_goods_board b left join used_goods_user u
on b.writer_id = u.user_id
where status='done'
group by WRITER_ID
having total_sales >= 700000
order by total_sales asc;


-- order by 하고 어떤 열을 기준으로 asc,desc 할건지 열! 적어주기
-- order by 열이름 asc/desc
-- done인 애들을 그룹묶어줘야돼서 where먼저 써줌
-- from - where - group by - having - select - order by 순!!
