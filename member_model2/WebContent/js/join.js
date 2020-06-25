/**
 * joinForm.jsp 유효성 검증하기
 */

// 규칙 
// 아이디 : 영문자, 숫자, 특수문자 조합으로 6~12자리
// 비번 : 영문자, 숫자, 특수문자 조합으로 8~15자리
// 이름 : 2~4자리 입력 가능
// 성별 : 필수입력
// 이메일 : 필수입력, 이메일 검증

$(function(){
	$("#joinform").validate({
		rules:{
			userid:{
				required:true,
				validID:true,
				remote : {
					url : "../checkId.do",
					type : "post",
					data : {
						userid : function(){
							return $('#userid').val();
						}
					}
				}
			},
			password:{
				required:true,
				validPWD:true
			},
			confirm_password:{
				required:true,
				validPWD:true,
				equalTo:"#password"
			},
			name:{
				required:true,
				rangelength:[2,4]
			},
			gender:{
				required:true
			},
			email:{
				required:true,
				email:true
			}
		},
		
		messages:{
			userid:{
				required:"(필수항목)",
				remote : "이 아이디는 사용중입니다."
			},
			password:{
				required:"(필수항목)"
			},
			confirm_password:{
				required:"(필수항목)",
				equalTo:"(비밀번호 입력 불일치)"
			},
			name:{
				required:"(필수항목)"
			},
			gender:{
				required:"(필수항목)"
			},
			email:{
				required:"(필수항목)",
				email:"(메일 형식 확인)"
			}
		},
		
		errorPlacement:function(error, element){ //에러메시지 위치지정
			$(element).closest("form").find("small[id='" + element.attr("id") + "']").append(error);
		}
	})
})

// 규칙검증 메소드
$.validator.addMethod("validID", function(value){
	const regId = /(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,12}/;
	return regId.test(value);
}, "영문자, 숫자, 특수문자의 조합으로 6~12자리");

$.validator.addMethod("validPWD", function(value){
	const regPwd = /^(?=[A-Za-z])(?=.+\d)[A-Za-z\d]{8,15}$/;
	return regPwd.test(value);
}, "영문자, 숫자의 조합으로 8~15자리");







