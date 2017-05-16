
let initialState = {}

if (window.__INITIAL_STATE__.app) {
  initialState = window.__INITIAL_STATE__.app.data.handmadeApi
}

export const handmade = (state = initialState) => {
  return state
}