-- 코드를 입력하세요
SELECT CAR_ID,
    CASE 
        when CAR_ID in
    (select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY where '2022-10-16' between start_date and end_date)
    then '대여중'
    else '대여 가능'
    end 'AVAILABILITY'
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID
order by car_id desc;
