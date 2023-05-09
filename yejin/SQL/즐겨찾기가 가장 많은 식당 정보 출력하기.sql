select REST_INFO.FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from REST_INFO inner join 
        (select FOOD_TYPE, max(favorites) as max_fav
         from REST_INFO
         group by FOOD_TYPE) temp 
    on REST_INFO.food_type = temp.food_type and REST_INFO.favorites = temp.max_fav
order by FOOD_TYPE desc;
