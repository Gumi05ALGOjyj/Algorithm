-- 코드를 입력하세요
SELECT FOOD_TYPE,REST_ID, REST_NAME,FAVORITES
from REST_INFO 
where (FOOD_TYPE, FAVORITES) in
(select food_type, max(FAVORITES) from rest_info
group by food_type)
order by food_type desc;
