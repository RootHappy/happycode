//user.thrift

namespace java com.wang.thrift.bean

struct User {
    1:required string username;     //用户姓名
    5:required i32 age;                     //用户年龄
    10:optional i64 birthDay;           //用户生日
}