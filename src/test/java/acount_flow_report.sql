-- 11月9日分红流水 530
select * from account_flow_history_copy 
where create_time >= '20171111' and id >= 13556 and create_id = 0 

-- 生产环境除去备份里的流水
select p.* from account_flow_history_copy p
where p.id not in
(select t.id from account_flow_history t ) 

-- 生产环境客户手动操作流水
select p.* from account_flow_history_copy p
where p.id not in
(select t.id from account_flow_history t ) 
and  p.create_id > 0

-- 生产环境除去备份里的流水 出
select p.* from account_flow_history_copy p
where p.id not in
(select t.id from account_flow_history t ) and p.type = 1 

-- 生产环境除去备份里的流水 入
select p.* from account_flow_history_copy p
where p.id not in
(select t.id from account_flow_history t ) and p.type = 2 


-- 最新流水汇总
SELECT
ad.member_id,
ad.ad_total,
ad.ad_bonus,
ad.ad_seed,
rd.rd_total,
ad.ad_total-IFNULL(rd.rd_total,0) as act_total,
ad.ad_bonus-IFNULL(rd.rd_bonus,0) as act_bonus,
ad.ad_seed-IFNULL(rd.rd_seed,0) as act_seed
from
-- 生产环境除去备份里的流水 入
(select p.member_id,ifnull(SUM(p.total_amt),0) as ad_total,IFNULL(SUM(p.bonus_amt),0) as ad_bonus,IFNULL(sum(p.seed_amt),0)as ad_seed
from account_flow_history_copy p
where p.id not in
(select t.id from account_flow_history t ) and p.type = 2 
GROUP BY p.member_id) ad
LEFT JOIN 
-- 生产环境除去备份里的流水 出
(select p.member_id,ifnull(SUM(p.total_amt),0) as rd_total,ifnull(SUM(p.bonus_amt),0) as rd_bonus,ifnull(sum(p.seed_amt),0)as rd_seed
from account_flow_history_copy p
where p.id not in
(select t.id from account_flow_history t ) and p.type = 1 
GROUP BY p.member_id) rd
on ad.member_id = rd.member_id

SELECT * FROM
account_flow_history
where
type = 1 and total_amt is null

update account_flow_history set total_amt = bonus_amt,seed_amt = 0
where type = 1 and total_amt is null

SELECT * FROM
account_flow_history
where
type = 2 and total_amt is null

update account_flow_history set total_amt = bonus_amt,seed_amt = 0
where type = 2 and total_amt is null

select * from transfer where member_id = 247;


SELECT 
tep.member_id,
tep.total_bonus as '备份余额', -- 备份时账户余额
act.act_total as '流水汇总', -- 流水差异汇总
tep.total_bonus + act.act_total as '备份+流水', -- 实际账户余额
prd.total_bonus as '生产余额', -- 生产环境账户余额
tep.total_bonus + act.act_total - prd.total_bonus as '差异金额',
tep.bonus_amt,
act.act_bonus,
tep.bonus_amt + act.act_bonus as actual_bonus,
prd.bonus_amt as prd_bonus_amt,
tep.seed_amt,
act.act_seed,
tep.seed_amt + act.act_seed as actual_seed,
prd.seed_amt as prd_seed_amt
FROM
(
      -- 备份时的账户数据
			SELECT t.id,t.member_id,t.total_bonus,t.bonus_amt,t.seed_amt
			from account_manager t 
)tep
LEFT JOIN
(
			SELECT
			ad.member_id,
			ad.ad_total,
			ad.ad_bonus,
			ad.ad_seed,
			rd.rd_total,
			ad.ad_total-IFNULL(rd.rd_total,0) as act_total,
			ad.ad_bonus-IFNULL(rd.rd_bonus,0) as act_bonus,
			ad.ad_seed-IFNULL(rd.rd_seed,0) as act_seed
			from
			-- 生产环境除去备份里的流水 入
			(select p.member_id,ifnull(SUM(p.total_amt),0) as ad_total,IFNULL(SUM(p.bonus_amt),0) as ad_bonus,IFNULL(sum(p.seed_amt),0)as ad_seed
			from account_flow_history_copy p
			where p.id not in
			(select t.id from account_flow_history t ) and p.type = 2 
			GROUP BY p.member_id) ad
			LEFT JOIN 
			-- 生产环境除去备份里的流水 出
			(select p.member_id,ifnull(SUM(p.total_amt),0) as rd_total,ifnull(SUM(p.bonus_amt),0) as rd_bonus,ifnull(sum(p.seed_amt),0)as rd_seed
			from account_flow_history_copy p
			where p.id not in
			(select t.id from account_flow_history t ) and p.type = 1 
			GROUP BY p.member_id) rd
			on ad.member_id = rd.member_id
) act  
on tep.member_id = act.member_id
LEFT JOIN 
-- 生产环境最新的账户数据
account_manager_copy prd  on prd.member_id = tep.member_id
-- 备份账户数据 + 最新流水数据 不等于 生产账户数据
where tep.total_bonus + act.act_total  != prd.total_bonus