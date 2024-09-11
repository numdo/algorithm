-- 코드를 작성해주세요

select e.id,e.genotype,p.genotype as parent_genotype
from ecoli_data as e
join 
    (select id,parent_id,genotype
    from ecoli_data) as p
on e.parent_id = p.id
where bin(e.genotype & p.genotype)=bin(p.genotype)
order by e.id;

# select bin('101' & '101')