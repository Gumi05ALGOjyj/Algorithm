SELECT i.animal_id, i.name
from animal_ins i
    join animal_outs o
    using (animal_id)
where i.datetime>o.datetime
order by i.datetime
;
