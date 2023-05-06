-- 코드를 입력하세요
SELECT CAR_ID from CAR_RENTAL_COMPANY_CAR 
where CAR_TYPE ='세단'
and car_id in 
(select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY where start_date >= '2022-10-01 00:00:00')
order by car_id desc;
