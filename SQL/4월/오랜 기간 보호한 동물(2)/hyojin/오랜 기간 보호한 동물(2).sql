SELECT ins.animal_id, ins.name
from animal_ins ins left join animal_outs outs
using(animal_id)
where outs.datetime is not null
order by ins.datetime - outs.datetime asc
limit 2;
