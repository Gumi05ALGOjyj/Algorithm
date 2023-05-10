select a.user_id,
    a.nickname,
    concat(city," ",street_address1," ",street_address2) "전체주소",
    concat(substring(a.tlno,1,3),"-",substring(a.tlno,4,4),"-",substring(a.tlno,8,4)) "전화번호"
from used_goods_user a, (select writer_id, count(*) "cnt"
                        from used_goods_board
                        group by writer_id
                        having cnt>=3) b
where a.user_id = b.writer_id
order by 1 desc
;
