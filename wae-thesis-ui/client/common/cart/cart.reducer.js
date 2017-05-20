let initialState = []

if (localStorage.waeCart) {
  initialState = JSON.parse(localStorage.waeCart)
}

export const cart = (state = initialState) => {
  return state
}