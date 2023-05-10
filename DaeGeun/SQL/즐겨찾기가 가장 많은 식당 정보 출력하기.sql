select FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from REST_INFO
where (food_type,favorites) in (select food_type, max(favorites)
                                from REST_INFO
                                group by FOOD_TYPE)
order by food_type desc
;
