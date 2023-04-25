select a.USER_ID, a.NICKNAME, b.TOTAL_SALES
from used_goods_user a,
    (select writer_id, sum(price) "TOTAL_SALES"
    from used_goods_board
    where status = "DONE"
    group by writer_id
    having TOTAL_SALES>=700000) b
where a.user_id = b.writer_id
order by TOTAL_SALES
;
