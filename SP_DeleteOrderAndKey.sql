create proc DeleteOrderAndKey
as
delete from Securities where datediff(day, created, getdate()) > 2

delete from Orders where stat=-2 and datediff(day, datebuy, getdate()) > 2 
go

