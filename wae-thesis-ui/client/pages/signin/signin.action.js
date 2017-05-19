import axios from 'axios'

export const userSignInRequest = (user) => {
  return dispatch => {
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:8001/authenticateAccount', {
        headers: {
          "Account_Header_Param": JSON.stringify(user)
        }
      })
        .then(
          res => {
            let authorization = res.headers.authorization

            if(authorization) {
              let { token } = JSON.parse(authorization)
              let newToken = `Bearer ${token}`
              localStorage.jwtAuthorization = newToken

              axios.get('http://localhost:8001/api/account', {
                headers: {
                  "Authorization": newToken
                }
              })
                .then(
                  res => {
                    resolve()
                  }
                )
            } else {
              reject("*Authentication Failed")
            }
          }
        )
    })
  }
}