-- 헤비 유저가 소유한 장소
SELECT id, name, host_id
from places
where host_id in (select host_id from places group by host_id having count(host_id) >= 2)
order by id;
