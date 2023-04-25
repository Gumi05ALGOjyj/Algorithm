select ins.name, ins.datetime
from animal_ins ins left join animal_outs outs
using(animal_id)
where outs.datetime is null
order by ins.datetime asc
limit 3;


-- 동물 보호소에 들어온 동물의 정보(전체 동물)에 입양 보낸 동물의 정보를 join
-- 들어온 동물들 전체 리스트에 입양 보내진 애들한테만 입양 보낸 정보가 붙음
-- 둘이 column이 animal_id로 중복되므로 using
-- 입양 못간 애들은 join한 데이터(입양 보낸 동물의 입양일)가 널일것임
-- 여기서 datetime column명은 같으나 보호시작일, 입양일로 다른 컬럼인것에 주의
