-- 코드를 입력하세요
SELECT CAR_ID
    (case when max(start_date)='2022-10-16' or max(end_date)>'2022-10-15' then '대여중' 
    else '대여 가능'
    end as AVAILABILITY)
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID;
