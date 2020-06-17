/**
 * 
 */

$(function(){
	$("#modifyform").validate({
		rules:{
			current_password:{
				required:true,
			},
			new_password:{
				required:true,
				validPWD:true
			},
			confirm_password:{
				required:true,
				equalTo:"#new_password"
			}
		},
		
		messages:{
			current_password:{
				required:"(필수항목)",
			},
			new_password:{
				required:"(필수항목)",
			},
			confirm_password:{
				required:"(필수항목)",
				equalTo:"비밀번호 입력 불일치"
			}			
		},
		
		errorPlacement:function(error, element){ //에러메시지 위치지정
			$(element).closest("form").find("small[id='" + element.attr("id") + "']").append(error);
		}
	
	})
})

// 수정할 비밀번호 검증
$.validator.addMethod("validPWD", function(value){
	const regPwd = /^(?=[A-Za-z])(?=.+\d)[A-Za-z\d]{8,15}$/;
	return regPwd.test(value);
}, "영문자, 숫자의 조합으로 8~15자리");
