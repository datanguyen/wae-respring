import axios from 'axios'

export const userSignUpRequest = (user) => {
  return dispatch => {
    let { username, password, fullname } = user
    let url = `http://localhost:8001/api/user/sign-up?username=${username}&password=${password}&info=${fullname}`

    return new Promise((resolve, reject) => {
      axios.post(url, {
        headers: {
          "Authorization": ""
        },
      })
        .then(
          res => {
            resolve("asdasd")
          }
        )
    })
  }
}