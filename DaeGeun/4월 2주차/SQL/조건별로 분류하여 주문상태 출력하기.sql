select order_id, product_id, date_format(out_date,'%Y-%m-%d') "out_date", 
    case when out_date<=date_format('2022-05-01','%Y-%m-%d') then "출고완료"
        when out_date>date_format('2022-05-01','%Y-%m-%d') then "출고대기"
        when ifnull(out_date,true) then "출고미정"
    end as "출고여부"
from food_order
order by order_id
;
