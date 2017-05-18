
let initialState = {}

if (window.__INITIAL_STATE__.app && window.__INITIAL_STATE__.app.data) {
  initialState = window.__INITIAL_STATE__.app.data.foodApi;
}

export const food = (state = initialState) => {
  return state
}