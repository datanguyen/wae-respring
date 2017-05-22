import axios from 'axios'

export const userCheckoutRequest = (checkout) => {
  return dispatch => {
    return new Promise((resolve, reject) => {
      let token = localStorage.jwtAuthorization
      let url = `http://localhost:8001/api/product/process-order`

      axios.post(url, {}, {
        headers: {
          "Authorization": token
        },
        params: {
          "checkoutData": JSON.stringify(checkout)
        }
      })
        .then(
          res => res.exception ? reject(res.exception) : resolve()
        )

    })
  }
}