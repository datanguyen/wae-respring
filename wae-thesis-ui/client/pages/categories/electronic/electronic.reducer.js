
let initialState = {}

if (window.__INITIAL_STATE__.app && window.__INITIAL_STATE__.app.data) {
  initialState = window.__INITIAL_STATE__.app.data.electronicApi
}

export const electronic = (state = initialState) => {
  return state
}